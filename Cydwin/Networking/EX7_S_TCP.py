import socket

s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.bind(("0.0.0.0", 7777))
s.listen(5)

while True:
    cs=s.accept()
    mesaj, adresa=cs.recvfrom(100)
    print(mesaj) 
    cs.sendto(mesaj, adresa)
