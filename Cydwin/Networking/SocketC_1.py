import socket

s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
s.sendto("mesaj", ("192.168.56.1",7777))

print(s.recvfrom(100))
