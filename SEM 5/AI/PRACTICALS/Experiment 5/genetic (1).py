from numpy.random import randint
from numpy.random import rand
import math

def binary_to_decimal(bin):
  decimal=0
  for i in range(len(bin)):
   decimal+=bin[i]*pow(2, 4-i)
  return decimal

def decimal_to_binary(dec):
	binaryVal=[]
	while(dec>0):
		binaryVal.append(dec%2)
		dec=math.floor(dec/2)
	for _ in range(5-len(binaryVal)):  
		binaryVal.append(0)
	binaryVal=binaryVal[::-1]
	return binaryVal

def crossover(parent1,parent2,r_cross):
	child1,child2 = parent1.copy(), parent2.copy()
	r = rand()
	point = 0
	if r > r_cross:
		point = randint(1,len(parent1)-2)
		child1 = parent1[:point] + parent2[point:]
		child2 = parent2[:point] + parent1[point:]
	return child1,child2,point

def mutation(chromosome,r_mut):
    for i in range(len(chromosome)):
        if rand()<r_mut:
        	chromosome[i] = 1 - chromosome[i]
    return chromosome

def fitness_function(x):
	return pow(x,2)

def genetic_algorithm(iterations, population_size, r_cross, r_mut):
	input = [randint(0, 32) for _ in range(population_size)] 
	pop = [decimal_to_binary(i) for i in input]
	for generation in range(iterations):
		print(f'\nITERATION : {generation+1}',end='\n\n')
		decimal = [binary_to_decimal(i) for i in pop]
		fitness_score = [fitness_function(i) for i in decimal]
		f_by_sum = [fitness_score[i]/sum(fitness_score) for i in range(population_size)]
		exp_cnt = [fitness_score[i]/(sum(fitness_score)/population_size) for i in range(population_size)]
		act_cnt = [round(exp_cnt[i]) for i in range(population_size)]
		print('SELECTION\n\nInitial \tDecimal Value\tFitness Score\t\tFi/Sum\t\tExpected \tActual ')
		for i in range(population_size):
			print(pop[i],'\t',decimal[i],'\t\t',fitness_score[i],'\t\t',round(f_by_sum[i],2),'\t\t',round(exp_cnt[i],2),'\t\t',act_cnt[i])
		print('Sum : ',sum(fitness_score))
		print('Average : ',sum(fitness_score)/population_size)
		print('Maximum : ',max(fitness_score),end='\n')
		max_count = max(act_cnt)
		min_count = min(act_cnt)
		max_count_index = 0
		for i in range(population_size):
			if max_count == act_cnt[i]:
				maxIndex=i
				break
		for i in range(population_size):
			if min_count == act_cnt[i]:
				pop[i] = pop[max_count_index]
		crossover_children = list()
		crossover_point = list()
		for i in range(0,population_size,2):
			child1, child2, point_of_crossover = crossover(pop[i],pop[i+1],r_cross)
			crossover_children.append(child1)
			crossover_children.append(child2)
			crossover_point.append(point_of_crossover)
			crossover_point.append(point_of_crossover)
		print("\nCROSS OVER\n\nPopulation\t\tMate\t Crossover Point\t Crossover Population")
		for i in range(population_size):
			if (i+1)%2 == 1:
				mate = i+2
			else:
				mate = i
			print(pop[i],'\t',mate,'\t',crossover_point[i],'\t\t\t',crossover_children[i])
		mutation_children = list()
		for i in range(population_size):
			child = crossover_children[i]
			mutation_children.append(mutation(child,r_mut))
		new_population = list()
		new_fitness_score = list()
		for i in mutation_children:
			new_population.append(binary_to_decimal(i))
		for i in new_population:
			new_fitness_score.append(fitness_function(i))
		print("\nMUTATION\n\nMutation population\t New Population\t Fitness")
		for i in range(population_size):
			print(mutation_children[i],'\t',new_population[i],'\t\t',new_fitness_score[i])
		print('Sum : ',sum(new_fitness_score))
		print('Maximum : ',max(new_fitness_score))
		pop = mutation_children
		print("---------------------------------------------------------------------------------------------------")

def main():
	iterations = 3
	population_size = 4
	r_cross = 0.5
	r_mut = 0.05
	genetic_algorithm(iterations,population_size,r_cross,r_mut)

if __name__ == '__main__':
	main()