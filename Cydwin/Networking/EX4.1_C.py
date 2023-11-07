import socket

s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)

s.connect(("192.168.56.1",7777))
str = raw_input("C: ")
s.send(str.encode())
s.send()
print s.recv(1024).decode()
s.close()
