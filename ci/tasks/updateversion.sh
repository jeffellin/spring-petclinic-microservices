ls -la
VERSION=$(cat version/version)
cp -r source-code-gitops/. gitops
sed -r  "s/^(\s*)(newTag\s*:.*$)/\1newTag: \"${VERSION}\"/" gitops/app/kustomization.yaml
sed -r -i "s/^(\s*)(newTag\s*:.*$)/\1newTag: \"${VERSION}\"/" gitops/app/kustomization.yaml
cd gitops
git config --global user.name "YOUR NAME"
git config --global user.email "none@none.com"
git add .
git commit -m "update by ci"