=========================================================================
VM Virtual Network Adapter (vmnetcfg.exe)

To start the virtual network adapter in vmplayer 6 you need vmnetcfglib.dll and vmnetcfg.exe from a vmware workstation installation. One can get these files from the links below.

1) Ensure that you can start the Virtual Network Editor to configure port mapping
Virtual network editor for VMPlayer6.x and 7.x
http://ninety9names.blogspot.co.at/2013/12/download-vmnetcfgexe-and-vmnetcfglibdll.html
https://communities.vmware.com/thread/456842?start=15&tstart=0

2) configure port mapping
http://blog.fardad.com/2012/06/vmware-player-and-custom-nat-port-map.html
http://www.howtogeek.com/howto/vmware/allow-access-to-a-vmware-virtual-machinenat-from-another-computer/

===========================================================================

Access from android device to host computer

On host computer:
ipconfig tells 192.168.43.100

Check:
This address can be pinged from android device with "conectbot" app or from adb shell inside vmplayer
adb shell
netcfg

Wireless LAN adapter network connection IP Adress of host computer:
192.168.43.100


===========================================================================

Access VMPlayer from host computer

In VMPlayer:
ipconfig gives
IP: 192.168.216.128
Standardgateway: 192.168.216.2

In Virtual Network Editor (NAT Settings) shows the 192.168.216.2 as Gateway IP.

In the host computer the wamp server can be accessed via browser with http://192.168.216.128:8090

- httpd.conf (changed "require local" to "require all granted") to access webserver from outside vmplayer
#    Require local
Require all granted

===========================================================================

Port Forwarding in Virtual Network Editor
Host Port: 80 (The android device can call this port and will be forwarded to the port on the virtual machine. Here set to the default port)
Virtual machine IP address: 192.168.216.128 (Address from host to virtual machine. When host address is called from android device, then the call will be forwarded to this address)
Virtual machine port: 8090 (The wamp server port on the virtual machine)

===========================================================================

test change
