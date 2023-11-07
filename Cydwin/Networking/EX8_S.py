import socket

s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
s.bind(("0.0.0.0", 7777))

while True:
    mesaj, adresa=s.recvfrom(100)
    print(mesaj) 
    s.sendto(mesaj, adresa)
