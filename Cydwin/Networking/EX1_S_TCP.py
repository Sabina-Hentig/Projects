import socket

s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
s.bind(("0.0.0.0", 7777))
s.listen(5)
cs=s.accept()
mesaj, adresa=s.recvfrom(100)
print(mesaj)
print(adresa)
s.sendto(mesaj, adresa)