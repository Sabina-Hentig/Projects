import socket

s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
s.sendto("Ana are 20 de mere", ("192.168.56.1",7777))
vowel = set("aeiouAEIOU")

mesaj, adresa=s.recvfrom(100)
words = mesaj.split()
vowelCount = len([chr for chr in mesaj if chr in vowel])
longestVowelWordCount = len(max(words,key=vowelCount))
longestVowelWord = max(words,key=vowelCount)
print(longestVowelWord, longestVowelWordCount)