<h1>RouterControl</h1>
<b>Asus WRT support only at the moment. More to come</b>
<h3>How to install:</h3>
* git clone https://github.com/FedoraTipper/RouterControl.git
* cd RouterControl
* mv /complete/RouterControl.jar “Directory of your choosing”/
* nano/gedit/vim/vi (Who cares) RouterControl
* Edit the directory variable to the directory of the jar. Include .jar
* mv RouterControl /bin/

<h3>How-to use:</h3>
Depending on your installation the default install will make the script global.

To run the program/jar, input the following.

<b>Switch ISP:</b>
RouterControl [-s or --switch] [ISP linked header]		
The name/header depends on the header you set on the ISP when adding the details.

E.g. RouterControl -s [vox or 1]

<b>To restart DSL of the router:</b>

RouterControl [-r or --restart]

<b>To add an ISP and details:</b>

RouterControl [--add] [Header name] [username of ISP account] [password of ISP account]

E.g. RouterControl --add AH1 example@afrihost.co.za password123

<b>To add your Asus router settings: (Each run will overwrite current settings)</b>

RouterControl --config [ip of router] [username of router] [password of ISP]

E.g. RouterControl --config 192.168.1.1 admin password123

<h4>What's planned:</h4>
* Windows support very soon
* Frontend GUI
* Other router support
* More backend support
* More functionality
