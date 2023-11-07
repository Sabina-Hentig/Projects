import socket

s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.connect(("192.168.56.1",7777))
s.sendto("A", ("192.168.56.1",7777))

mesaj, adresa=s.recvfrom(100)
print(mesaj*2)