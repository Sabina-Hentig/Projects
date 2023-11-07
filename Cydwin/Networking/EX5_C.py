import socket

s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
s.sendto("Ana are ", ("192.168.56.1",7777))
s.sendto(" 20 ", ("192.168.56.1",7777))
s.sendto(" mere ", ("192.168.56.1",7777))
s.sendto(" si ", ("192.168.56.1",7777))
s.sendto(" 20 pere.", ("192.168.56.1",7777))

mesaj1, adresa=s.recvfrom(100)
mesaj2, adresa=s.recvfrom(100)
print(mesaj1)
print(mesaj2)
