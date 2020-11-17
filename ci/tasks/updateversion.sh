ls -la
VERSION=$(cat version/version)
MODULE=$APP_NAME
MODULE_KEY="${APP_NAME//-}"
cp -r source-code-gitops/. gitops

FILE=gitops/app/locks/$MODULE.yml

if [ -f $FILE ]; then
  echo "file already exists"
  rm $FILE
fi

echo "#@data/values" >> $FILE
echo '#@ load("@ytt:overlay", "overlay")' >> $FILE
echo "---" >> $FILE
echo "image:" >> $FILE
echo "  #@overlay/match missing_ok=True" >> $FILE
echo "  $MODULE: $VERSION" >> $FILE

cd gitops
git conficdg --global user.name "YOUR NAME"
git config --global user.email "none@none.com"
git add .
git commit -m "update by ci"
git pull -r