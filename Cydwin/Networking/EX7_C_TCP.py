import socket

s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.connect(("192.168.56.1",7777))
s.sendto("Ana are douazeci de mere",("192.168.56.1",7777))
mesaj=s.recvfrom(100)
longestWordCount = len(max((mesaj.split()),key=len))
longestWord = max((mesaj.split()), key=len)

print(longestWord, longestWordCount)
