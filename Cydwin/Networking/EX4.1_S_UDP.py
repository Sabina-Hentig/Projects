import socket

s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)  
s.bind(("0.0.0.0",7777))                           

mesaj, adresa=s.recvfrom(100)
print mesaj 
port=adresa[1]
numbers=[int(x) for x in str(port)]
print port
result= sum(numbers)
print result
s.sendto(str(result), adresa)        