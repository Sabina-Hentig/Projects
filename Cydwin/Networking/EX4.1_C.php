<?php

$s=socket_create(AF_INET, SOCK_DGRAM,0);
socket_sendto($s,"Sabina", 10, 0, "192.168.56.1",7777);
socket_recvfrom($s,$m,10,0,$rip, $rp);
echo $m;
?>