from collections import defaultdict
import csv


class Apriori(object):
    def __init__(self, minSupp, minConf):
        """ Parameters setting
        """
        self.minSupp = minSupp  # min support (used for mining frequent sets)
        self.minConf = minConf  # min confidence (used for mining association rules)

    def fit(self, filePath):
        """ Run the apriori algorithm, return the frequent *-term sets. 
        """
        # Initialize some variables to hold the tmp result
        transListSet  = self.getTransListSet(filePath)   # get transactions (list that contain sets)
        itemSet       = self.getOneItemSet(transListSet) # get 1-item set
        itemCountDict = defaultdict(int)         # key=candiate k-item(k=1/2/...), value=count
        freqSet       = dict()                   # a dict store all frequent *-items set
        
        self.transLength = len(transListSet)     # number of transactions
        self.itemSet     = itemSet
        
        # Get the frequent 1-term set
        freqOneTermSet = self.getItemsWithMinSupp(transListSet, itemSet, 
                                             itemCountDict, self.minSupp)

        # Main loop
        k = 1
        currFreqTermSet = freqOneTermSet
        while currFreqTermSet != set():
            freqSet[k] = currFreqTermSet  # save the result
            k += 1
            currCandiItemSet = self.getJoinedItemSet(currFreqTermSet, k) # get new candiate k-terms set
            currFreqTermSet  = self.getItemsWithMinSupp(transListSet, currCandiItemSet, 
                                                   itemCountDict, self.minSupp) # frequent k-terms set
            
            
        #
        self.itemCountDict = itemCountDict # 所有候选项以及出现的次数(不仅仅是频繁项),用来计算置信度啊
        self.freqSet       = freqSet       # Only frequent items(a dict: freqSet[1] indicate frequent 1-term set )
        return itemCountDict, freqSet
            
            
    def getSpecRules(self, rhs):
        """ Specify a right item, construct rules for it
        """
        if rhs not in self.itemSet:
            print('Please input a term contain in the term-set !')
            return None
        
        rules = dict()
        for key, value in self.freqSet.items():
            for item in value:
                if rhs.issubset(item) and len(item) > 1:
                    item_supp = self.getSupport(item)
                    item = item.difference(rhs)
                    conf = item_supp / self.getSupport(item)
                    if conf >= self.minConf:
                        rules[item] = conf
        return rules
        
    
    def getSupport(self, item):
        """ Get the support of item """
        return self.itemCountDict[item] / self.transLength
        
        
    def getJoinedItemSet(self, termSet, k):
        """ Generate new k-terms candiate itemset"""
        return set([term1.union(term2) for term1 in termSet for term2 in termSet 
                    if len(term1.union(term2))==k])
    
        
    def getOneItemSet(self, transListSet):
        """ Get unique 1-item set in `set` format 
        """
        itemSet = set()
        for line in transListSet:
            for item in line:
                itemSet.add(frozenset([item]))
        return itemSet
        
    
    def getTransListSet(self, filePath):
        """ Get transactions in list format 
        """
        transListSet = []
        with open(filePath, 'r') as file:
            reader = csv.reader(file, delimiter=',')
            for line in reader:
                transListSet.append(set(line))                
        return transListSet
                
    
    def getItemsWithMinSupp(self, transListSet, itemSet, freqSet, minSupp):
        """ Get frequent item set using min support
        """
        itemSet_  = set()
        localSet_ = defaultdict(int)
        for item in itemSet:
            freqSet[item]   += sum([1 for trans in transListSet if item.issubset(trans)])
            localSet_[item] += sum([1 for trans in transListSet if item.issubset(trans)])
        
        # Only conserve frequent item-set 
        n = len(transListSet)
        for item, cnt in localSet_.items():
            itemSet_.add(item) if float(cnt)/n >= minSupp else None
        
        return itemSet_