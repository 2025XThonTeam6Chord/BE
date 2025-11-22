# 🧣 넌 잘못이 없어 (It's not your fault) - Backend API Server

> **2025 X-Thon Team '내가 뭘 잘못했지?'**
>
> **AX(AI Transformation) 기반 청년 심리 위기 조기 감지 및 아웃바운드 케어 서비스**

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-Cache%20%26%20Pub%2FSub-DC382D?style=for-the-badge&logo=redis&logoColor=white)
![Gemini](https://img.shields.io/badge/AI-Gemini%20Pro-8E75B2?style=for-the-badge&logo=google&logoColor=white)

---

<br>

## 📖 Project Overview

**"청년의 성장은 항상 우상향하지 않습니다."**

'넌 잘못이 없어'는 별도의 앱 설치 없이 학교 **LMS(학습관리시스템) 및 웹 포털에 기생(Embedded)**하여 작동하는 마이크로 심리 검사 모듈입니다.
기존의 수동적인 상담 센터(Inbound)의 한계를 넘어, 데이터 기반으로 위기 징후를 먼저 포착하고 손을 내미는 **아웃바운드(Outbound) 멘탈 케어**를 지향합니다.

### 🎯 Core Values
```
Zero Friction (무마찰성): 학생들의 일상적 웹 환경(과제 제출, 성적 확인 등)에 자연스럽게 녹아든*Micro-Test로 진입 장벽을 제거
Data-Driven (데이터 기반): 단순 룰이 아닌, 시계열 데이터 분석을 통해 일시적 우울과 만성적 위기 징후를 조기 포착
Active Care (능동적 개입): 위험 감지 시 AI가 먼저 위로를 건네고 상담 센터로 연계하는 능동적 개입 시스템
```

---

<br>


## 🏗️ System Architecture

<img width="399" height="304" alt="image" src="https://github.com/user-attachments/assets/35dab8b9-c04f-4b1f-a4e0-6dc511de19b6" />

---

<br>

## 💾 ERD (Entity Relationship Diagram)

<img width="404" height="313" alt="image" src="https://github.com/user-attachments/assets/bd97e6a6-ccc0-46c3-a155-7910a96dee61" />

---

<br>

## 💻 Key Technologies (Core Algorithms)

### 1. Adaptive Question Engine (문항 추천 엔진)
사용자의 현재 심리 상태와 상황(Context)에 따라 최적의 질문을 실시간으로 결정

### 2. Hybrid Neuro-Symbolic AI (분석 엔진)
임상 심리학의 정해진 규칙과 LLM의 추론 능력을 결합
* **Logic Injection:** 프롬프트 레벨에서 PHQ-9/GAD-7 등의 표준 채점 로직을 강제하여 정량적 정확도 100% 확보.
* **RAG based Grounding:** 교내 상담 프로그램 및 실제 복지 데이터를 참조해 환각 없는 실질적 솔루션 제공.

---

<br>

## ⚙️ Tech Stack

| Category | Technology | Description |
| :--- | :--- | :--- |
| **Language** | Java 17 | LTS 버전, Record 패턴 등 최신 문법 활용 |
| **Framework** | Spring Boot 3.2 | WebMVC, JPA, Validation |
| **Database** | MySQL 8.0 | 메인 데이터 저장소 (JPA/Hibernate) |
| **Cache/Msg** | Redis | 세션 관리, 응답 캐싱, 비동기 이벤트 브로커 |
| **AI Model** | Gemini Pro | 심리 분석 리포트 생성 및 감정 톤 분석 |
| **Docs** | Swagger (OpenAPI) | API 명세서 자동화 |
| **Infra** | AWS EC2, Docker | 배포 및 컨테이너 환경 구축 |
