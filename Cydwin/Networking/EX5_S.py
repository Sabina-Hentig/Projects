import socket

import socket
s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
s.bind(("0.0.0.0", 7777))

while True:
    mesaj1, adresa=s.recvfrom(100)
    mesaj2, adresa=s.recvfrom(100)
    mesaj3, adresa=s.recvfrom(100)
    mesaj4, adresa=s.recvfrom(100)
    mesaj5, adresa=s.recvfrom(100)
    
    print(mesaj1)
    print(mesaj2)
    print(mesaj3)
    print(mesaj4)
    print(mesaj5)
    s.sendto((mesaj1+mesaj5), adresa)
    s.sendto((mesaj1+mesaj2+mesaj3), adresa)