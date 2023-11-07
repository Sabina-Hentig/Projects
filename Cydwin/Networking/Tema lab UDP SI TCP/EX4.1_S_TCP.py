import socket

s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.bind(("0.0.0.0",7777))
s.listen(5)
cs, addr=s.accept()
m=cs.recv(10)
print m
port=addr[1]
numbers=[int(x) for x in str(port)]
print port
result= sum(numbers)
print result
cs.sendto(str(result), addr)
cs.close()