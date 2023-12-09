comm = MPI.COMM_WORLD
rank = comm.Get_rank()
print("RANK", rank)
if rank == 0:
    idata = "Hello Junaid"
    comm.send(idata, dest=1)
elif rank == 1:
    idata = comm.recv(source=0)
    print('On process 1, data is ',idata)