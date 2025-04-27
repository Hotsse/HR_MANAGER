# HR_MANAGER
그룹웨어 인사시스템

## Spec
- Spring Boot 2.7.18
- Java 21
- Maven Build
- React 18 + Vite
- H2 Database
- JPA
- Docker


## Docker 환경 구성
아래 Container Registry 를 참고하여 테스트

### HR_MANAGER API 서비스
- https://github.com/users/Hotsse/packages/container/package/hr-manager-api
``` bash
docker pull ghcr.io/hotsse/hr-manager-api:latest
docker run --platform=linux/amd64 -p 8080:8080 ghcr.io/hotsse/hr-manager-api:latest
```

### HR_MANAGER Frontend 서비스
- https://github.com/users/Hotsse/packages/container/package/hr-manager-fe
``` bash
docker pull ghcr.io/hotsse/hr-manager-fe:latest
docker run --platform=linux/amd64 -p 80:80 ghcr.io/hotsse/hr-manager-fe:latest
```
