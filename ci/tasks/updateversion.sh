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
echo "---" >> $FILE
echo "$MODULE_KEY:" >> $FILE
echo "  image: $VERSION" >> $FILE

cd gitops
git config --global user.name "YOUR NAME"
git config --global user.email "none@none.com"
git add .
git commit -m "update by ci"