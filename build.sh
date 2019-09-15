#!/bin/sh

cd frontend/shopping-front/
yarn install
yarn run build

cd -
mkdir -p src/main/resources/static
cp -rf frontend/shopping-front/build/* src/main/resources/static/



