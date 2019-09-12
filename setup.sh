#!/bin/bash
oc new-project beer-demo-qa
oc new-project beer-demo-prod
oc new-app fuse7-java-openshift:1.2~https://github.com/rh-forum-demo-2019/fuse-implementation.git -n beer-demo-qa

