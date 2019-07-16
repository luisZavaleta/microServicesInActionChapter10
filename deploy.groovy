def toKubernetes(tagToDeploy, namespace, deploymentName) {
  sh("sed -i.bak 's#BUILD_TAG#${tagToDeploy}#' ./deploy/${namespace}/*.yml")




 container('kubectl') {
    sh("kubectl --namespace=${namespace} apply -f deploy/${namespace}/")
  }
  
}



def rollback(deploymentName) {
  kubectl("rollout undo deployment/${deploymentName}")
}

return this;