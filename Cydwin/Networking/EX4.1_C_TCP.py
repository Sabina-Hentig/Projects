import socket

s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.connect(("192.168.56.1",7777))
msg=raw_input("C: ");
s.send(msg)
m=s.recv(10)
print m
s.close()