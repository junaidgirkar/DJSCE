import re
text_to_search = '''abcdefghijklmnopqrstuvwxyz
ABCDEFGHIJKLMNOPQRSTUVWXYZ
1234567890
123.456.789
123-456-789
123*456*789
cat 
mat
bat 
Mr. Smith
Mr David
Mrs. Riya
Mr. Ha HaHa
'''


def pattern_finder(pattern, texts):
    for text in texts.split('\n'):
        matches = pattern.finditer(text)
        for match in matches:
            print(match.group())


pattern_finder(re.compile(r'abc'), text_to_search)
pattern_finder( re.compile(r'^[a-zA-Z]'), text_to_search)
pattern_finder(re.compile(r'[^b]at'), text_to_search)
pattern_finder(re.compile(r'\d\d\d'), text_to_search)
pattern_finder(re.compile(r'\d{3}.\d{3}.\d{3}'), text_to_search)
pattern_finder(re.compile(r'Mr\.?'), text_to_search)
pattern_finder(re.compile(r'Mr\.?\s[A-Z]\w*'), text_to_search)
pattern_finder(re.compile(r'M(r|s|rs).?\s[A-Z]\w*'), text_to_search)


# #EMAILS
emails = '''khushali@gmail.com
khushali.deulkar@djsce.ac
khushali-123-deulkar@yahoo.com
'''
pattern_finder(re.compile(r'[a-zA-Z0-9.-].com'), emails)
pattern_finder(re.compile(r'[a-zA-Z0-9.-]+@[a-zA-Z-]+\.com'), emails)
pattern_finder(re.compile(r'[a-zA-Z0-9.-]+@[a-zA-Z-]+\.(com|ac|net)'), emails)

# URLS
urls = '''
https://www.google.com
https://youtube.com
http://djsce.ac.in
https://www.nasa.gov
'''
pattern_finder(re.compile(r'http'), urls)
pattern_finder(re.compile(r'https?'), urls)
pattern_finder(re.compile(r'https?://(www\.)?'), urls)

# Use of Group (index)
pattern=re.compile(r'https?://(www\.)?(\w+)(\.\w+)')
matches = pattern.finditer(urls)
for match in matches:
    print(match.group(0))
    print(match.group(2))
    print(match.group(3))

# Use of Sub
subbed_urls=pattern.sub(r'\1\2\3',urls)
print(subbed_urls)
