<?php

$s=socket_create(AF_INET,SOCK_STREAM,0);
socket_bind($s,"0.0.0.0",7777);
socket_listen($s);
$cs=socket_accept($s);
$socket_getpeername($cs,$address,$port);
$input = socket_read($cs, 1024);
echo $input;	

socket_close($cs);
socket_close($s);

?>