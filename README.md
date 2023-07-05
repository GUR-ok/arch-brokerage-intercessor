![img.png](img.png)

#### Инструкция по запуску:
1) `mvn clean package`
2) `docker build -t gurok/arch_brokerage_intercessor .`
3) `docker push gurok/arch_brokerage_intercessor`

   Для локального поднятия кафки: `docker-compose up`

- `minikube start`
- `kubectl create namespace arch-gur`
- Использовать nginx ingress controller установленный через хелм, а не встроенный в minikube:

```
kubectl delete namespace ingress-nginx
kubectl delete ingressClass nginx
kubectl create namespace m && helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/ && helm repo update && helm install nginx ingress-nginx/ingress-nginx --namespace m -f nginx-ingress.yaml
```

- `helm install gorelov-kafka ./deployment/kafka/`
- `helm install gorelov-brokerage-intercessor ./deployment/app/`
  `kubectl get pods -n arch-gur`
- В случае ошибки при деплое приложения через helm

  Error: INSTALLATION FAILED: Internal error occurred: failed calling webhook "validate.nginx.ingress.kubernetes.io": Post "https://ingress-nginx-controller-admission.ingress-nginx.svc:4
  43/networking/v1/ingresses?timeout=10s": dial tcp 10.111.50.42:443: connect: connection refused

  необходимо выполнить:
    ```
    kubectl get ValidatingWebhookConfiguration
    kubectl delete -A ValidatingWebhookConfiguration ingress-nginx-admission
    ```  
- дождаться поднятия подов


Админская панель доступна по адресу: http://localhost:8081/camunda/app/admin/default/#/
Установка Excamad: docker run -d -p 8080:8080 kotovdenis/excamad:latest
docker run -d -p 8085:8080 kotovdenis/excamad:latest
# http://localhost:8085/#/ excamad
В экзамаде ввести http://localhost:8081/engine-rest/
или http://<host>:<port>/<context-path>/engine-rest/

kubectl port-forward -n arch-gur arch-intercessor-deployment-76548647fd-bpxbj 8000:8000

---

#### Очистка пространства:

- `helm uninstall gorelov-intercessor`
- `helm uninstall nginx -n m`
- `helm uninstall gorelov-kafka`
- `kubectl delete namespace arch-gur`
- `kubectl delete namespace m`