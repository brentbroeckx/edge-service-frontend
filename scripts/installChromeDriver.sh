#!/bin/bash
CHROME_VERSION=$(google-chrome --version | cut -f 3 -d ' ' | cut -d '.' -f 1) \
CHROMEDRIVER_RELEASE=$(curl --location --fail --retry 3 http://chromedriver.storage.googleapis.com/LATEST_RELEASE_${CHROME_VERSION}) \
curl --silent --show-error --location --fail --retry 3 --output /tmp/chromedriver_linux64.zip "http://chromedriver.storage.googleapis.com/$CHROMEDRIVER_RELEASE/chromedriver_linux64.zip" \
cd /tmp \
unzip chromedriver_linux64.zip \
rm -rf chromedriver_linux64.zip \
sudo mv chromedriver /usr/local/bin/chromedriver \
sudo chmod +x /usr/local/bin/chromedriver \
chromedriver --version