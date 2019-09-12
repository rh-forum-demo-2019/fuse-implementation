#!/bin/bash
oc new-project beer-demo-qa
oc new-project beer-demo-prod
oc new-app fuse7-java-openshift:1.2~https://github.com/rh-forum-demo-2019/fuse-implementation.git -n beer-demo-qa
oc expose service fuse-implementation -n beer-demo-qa
oc policy add-role-to-user edit system:serviceaccount:cicd:jenkins -n beer-demo-qa
oc policy add-role-to-user edit system:serviceaccount:cicd:jenkins -n beer-demo-prod
oc new-app fuse-implementation -n beer-demo-prod
oc expose svc fuse-implementation -n beer-demo-prod
oc set triggers dc/fuse-implementation --manual -n beer-demo-prod
