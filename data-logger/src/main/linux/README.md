This directory contains scripts that can be copied to a linux system to easy start and stop of the
docker services.

1. Copy the current directory to your linux system's root `/`. The current directories structure is created in a way so that the files are placed at the right location.
2. Edit the `/usr/lib/sonnen/application.properties` 
3. EDIT the .env if needed: `/usr/lib/sonnen/.env`
4. Ensure the execution flag is set: `sudo chmod +x /usr/lib/sonnen/sonnen.sh`
5. Reload the systemd `sudo systemctl daemon-reload`. The service script can be found in `/etc/systemd/system/sonnen.service`
6. Start the sonnen data logger: `sudo systemctl start sonnen`

Stop the sonnen data logger: `sudo systemctl stop sonnen` or
restart the sonnen data logger: `sudo systemctl restart sonnen`.

Access the mongodb express web ui via `http://<HOST_OR_IP>`

