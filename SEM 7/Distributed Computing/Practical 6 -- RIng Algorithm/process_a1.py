import sys
import mutual_exclusion_ricart_agrawala
from mutual_exclusion_ricart_agrawala import Initialize_Process
import time

Initialize_Process(1, ('127.0.0.1', 6066), ((('127.0.0.1', 6067), ('127.0.0.1', 6068))), (2, 3), 2)
ts = 1
while ts != 0:
    print("Request CS with timestamp:")
    ts = int(input())
    print("Estimated time in seconds CS:")
    cstime=int(input())
    if ts != 0:
        mutual_exclusion_ricart_agrawala.Aquire_CS_Lock(ts)
        print('process 1 in Critical Section\n')
        time.sleep(cstime)
        mutual_exclusion_ricart_agrawala.Release_CS_Lock()


mutual_exclusion_ricart_agrawala.ExitProcess()
quit()
