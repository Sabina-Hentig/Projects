import socket

s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
s.sendto("AaBuCDEeF", ("192.168.56.1",7777))
vowel = set("aeiouAEIOU")

mesaj, adresa=s.recvfrom(100)
print(len([chr for chr in mesaj if not chr in vowel]))
