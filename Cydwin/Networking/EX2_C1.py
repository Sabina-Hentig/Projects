import socket

s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
s.sendto("ABCDEF", ("192.168.56.1",7777))

mesaj, adresa=s.recvfrom(100)
print(len(mesaj))
