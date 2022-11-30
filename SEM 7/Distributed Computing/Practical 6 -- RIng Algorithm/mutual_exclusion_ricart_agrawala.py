import sys
import time
import threading
import socket

# Message Status (The type of message for CS Lock - Request and Response)
MSG_RESPONSE = 0
MSG_REQUEST = 1

# Process Status (The variables carries status of processes at a given instance)
CS_REQUESTED = 0
CS_EXECUTING = 1
CS_NOTREQUIRED = 2

# Native Process Information
ProcessInfo  =  {
                    'ProcessID':        None,
                    'ProcessSocket':    None,
                    'ProcessState':     None,
                    'ProcessTimestamp': None,
                    'RemotProcess':     None,
                }

DefferedArray = {

                    1:                  None,
                    2:                  None,
                    3:                  None
                }

# To handle replies from remote processes against CS requests
ResponseQueue    = []
MsgThread = None

# Addresses of Remote Proesses
RemoteSockets = { }

MAXRECV = 4096
DEBUG = False



# Process Initialization and starting backgorund daemon process
def Initialize_Process(ProcessID, ProcessSocket, RemoteSocket, RemoteID, NumRemotes):
    if DEBUG: print('Entry: Process Initialization\n')
    #Initalize the local process info
    ProcessInfo['ProcessID']               = ProcessID
    ProcessInfo['ProcessSocket']           = ProcessSocket
    ProcessInfo['ProcessState']            = CS_NOTREQUIRED
    ProcessInfo['ProcessTimestamp']        = 0
    ProcessInfo['RemoteProcess']           = NumRemotes

    DefferedArray[1]       = 0
    DefferedArray[2]       = 0
    DefferedArray[3]       = 0


# Getting remote process details
    RemoteSocket1, RemoteSocket2 = RemoteSocket
    RemoteID1, RemoteID2 = RemoteID

    RemoteSockets[RemoteID1] = RemoteSocket1
    RemoteSockets[RemoteID2] = RemoteSocket2

# Create thread that is supposed to fork the messageListener function to run in the background
    msgThread = threading.Thread(target = MessageListener)
    msgThread.start()
    print('Process Initializated')



# Sending Message for CS Execution
def SendMessage(ProcessID, Message):
    if DEBUG: print('Entry : Sending Message to {0}'.format(ProcessID))
    # if DEBUG: print(str(message))
    # if DEBUG: print('Sending message --> {0}'.format(message['procInfo']['procName']))
    SendingSocket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    SendingSocket.sendto(str(Message).encode(), RemoteSockets[ProcessID])
    SendingSocket.close()

    if DEBUG: print('Exiting --> SendMessage')
    return True

# Listning Message for Local Process
def MessageListener():
    listeningSocket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

    #Bind the socket to the local address
    listeningSocket.bind(ProcessInfo['ProcessSocket'])

    while True:
        MessageHandler(listeningSocket.recv(MAXRECV))



# Handling Messages at Local Process
def MessageHandler(message):

    # Convert the string we get back to a dictionary
    RemoteMessage = eval(message)

    if DEBUG: print(RemoteMessage)
    if DEBUG: print(ProcessInfo)

    if RemoteMessage['MessageType'] == MSG_REQUEST:
        if ProcessInfo['ProcessState'] == CS_EXECUTING:
            if DEBUG: print("The process is executing CS")
            DefferedArray[RemoteMessage['ProcessID']] = 1

        elif ProcessInfo['ProcessState'] == CS_REQUESTED:
            if DEBUG: print("The process has requested CS")
            if RemoteMessage['Timestamp'] > ProcessInfo['ProcessTimestamp']:
                if DEBUG: print("The timestamp of remote process is smaller then local process")
                DefferedArray[RemoteMessage['ProcessID']] = 1

            elif ( RemoteMessage['Timestamp'] == ProcessInfo['ProcessTimestamp'] ) and ( RemoteMessage['ProcessID'] > ProcessInfo['ProcessID']):
                DefferedArray[RemoteMessage['ProcessID']] = 1
            else:
                r_message = { 'MessageType': MSG_RESPONSE, 'ProcessID': ProcessInfo['ProcessID']}
                SendMessage(RemoteMessage['ProcessID'], r_message)
        else:
            r_message = { 'MessageType': MSG_RESPONSE, 'ProcessID': ProcessInfo['ProcessID']}
            if DEBUG: print('Sent reply to --> {0}'.format(RemoteMessage['ProcessID']))
            SendMessage(RemoteMessage['ProcessID'], r_message)

    if RemoteMessage['MessageType'] == MSG_RESPONSE:
        if DEBUG: print ('Response recieved from --> {0}'.format(RemoteMessage['ProcessID']))
        if DEBUG: print (ResponseQueue);
        ResponseQueue.remove(RemoteMessage['ProcessID'])
    print("Differed Array:")
    print(DefferedArray)


# Aquiring CS Lock
def Aquire_CS_Lock(CurrentTimestamp):
    print('Aquiring CS Lock\n')
    # Change Process State : Requesting CS
    ProcessInfo['ProcessState'] = CS_REQUESTED
    ProcessInfo['ProcessTimestamp'] = CurrentTimestamp

    RequestMessage = { 'MessageType': MSG_REQUEST, 'ProcessID': ProcessInfo['ProcessID'],'Timestamp': ProcessInfo['ProcessTimestamp'] }

    #If we can't send any messages we assume that we are first and therefore can enter the section without any replies
    for ProcessID in RemoteSockets:
        if DEBUG: print(ProcessID)
        SendMessage(ProcessID, RequestMessage)
        ResponseQueue.append(ProcessID)

    print('Response Queue:')
    print(ResponseQueue)
    while len(ResponseQueue) > 0: pass #Wait for the replyQueue to empty before continuing
    ProcessInfo['ProcessState'] = CS_EXECUTING
    if DEBUG: print('Aquired CS Lock\n')
    return True


# Releasing CS Lock
def Release_CS_Lock():
    if DEBUG: print('Entry: Release CS\n')
    ProcessInfo['ProcessState'] = CS_NOTREQUIRED
    ResponseMessage = { 'MessageType': MSG_RESPONSE, 'ProcessID': ProcessInfo['ProcessID'] }
    for ProcessID in DefferedArray:
        if DefferedArray[ProcessID] == 1:
            SendMessage(ProcessID, ResponseMessage)
            DefferedArray[ProcessID]= 0

    print('DifferedArray after release of CS:')
    print(DefferedArray)
    print('CS Released')
    return True


# Exit Process
def ExitProcess():
    return True
