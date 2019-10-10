#!/bin/bash
oc new-project beer-demo-qa
oc new-app openjdk18-web-basic-s2i -p SOURCE_REPOSITORY_URL=https://github.com/rh-forum-demo-2019/fuse-implementation.git -p CONTEXT_DIR= --build-env='MAVEN_MIRROR_URL=http://nexus-cicd.18.184.165.137.nip.io/nexus/content/groups/public/' -n beer-demo-qa
oc logs build/openjdk-app-1 --follow
oc new-project beer-demo-prod
oc expose service fuse-implementation -n beer-demo-qa
oc policy add-role-to-user edit system:serviceaccount:cicd:jenkins -n beer-demo-qa
oc policy add-role-to-user edit system:serviceaccount:cicd:jenkins -n beer-demo-prod
oc project beer-demo-prod
oc tag beer-demo-qa/fuse-implementation:latest beer-demo-prod/fuse-implementation:latest
oc new-app fuse-implementation -n beer-demo-prod
oc expose svc fuse-implementation -n beer-demo-prod
oc set triggers dc/fuse-implementation --manual -n beer-demo-prod
oc create -f pipeline.yaml -n cicd
