RouterControl

How to install:
git clone https://github.com/FedoraTipper/RouterControl.git
cd RouterControl
mv /complete/RouterControl.jar “Directory of your choosing”/
nano/gedit/vim/vi (Who cares) RouterControl
Edit the directory variable to the directory of the jar. Include .jar
mv RouterControl /bin/

How-to use:
Depending on your installation the default install will make the script global.

To run the program/jar, input the following.

Switch ISP
RouterControl [-s or --switch] [ISP linked header]		
The name/header depends on the header you set on the ISP when adding the details.

E.g. RouterControl -s [vox or 1]

To restart DSL of the router:

RouterControl [-r or --restart]

To add an ISP and details

RouterControl [--add] [Header name] [username of ISP account] [password of ISP account]

E.g. RouterControl --add AH1 example@afrihost.co.za password123

To add your Asus router settings. Each run will overwrite current settings

RouterControl --config [ip of router] [username of router] [password of ISP]

E.g. RouterControl --config 192.168.1.1 admin password123

