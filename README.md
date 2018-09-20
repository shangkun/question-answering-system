# 问答系统 question-answering-system

## 开发语言及版本：Java 1.8
## 集成开发环境：IntelliJ IDEA
## 开发框架：SpringMVC、Mybatis
## 数据中间件：Mysql、Redis
## 项目构建工具：maven
## 版本控制：git
## 自然语言处理工具：Hanlp

## 目录结构

├─src
    ├─main
        ├─java
            ├─cn.ken.question.answering.system
                ├─common
                ├─component
                ├─config
                ├─controller
                ├─mapper
                ├─memorydb
                ├─model
                ├─service
                ├─utils
        ├─resources
            ├─cn.ken.question.answering.system.mapper
            ├─config
            ├─hanlp.properties
            ├─log4j2.xml
            ├─question_answering_system.sql
        ├─webapp
            ├─META-INF
            ├─views
            ├─WEB-INF
            ├─login.html
    ├─test
        ├─java
            ├─cn.ken.question.answering.system
                ├─controller
                ├─redis
                ├─service
                ├─JUnit4BaseConfig
    ├─.gittgnore
    ├─pom.xml
    ├─README.md
