# E-Commerce API (Spring Boot)

Bu proje, Spring Boot kullanılarak geliştirilmiş, **Katmanlı Mimari (Layered Architecture)** prensiplerine uygun bir E-Ticaret REST API uygulamasıdır.

## 🚀 Teknolojiler
- **Java 21**
- **Spring Boot**
- **Spring Data JPA (Hibernate)**
- **MySQL 8.0**
- **Maven**

## 🏗 Mimari Yapı
Proje, kodun sürdürülebilirliği ve temizliği açısından profesyonel standartlarda 3 ana katmana ayrılmıştır:
- **`web` (Controller, Request, Response):** Dışarıdan gelen HTTP isteklerini (REST) karşılar.
- **`api` (Service, DTO):** İş kurallarını (Business Logic) tanımlar ve dış dünya ile veritabanı arasında güvenli veri taşıma nesnelerini (DTO) yönetir.
- **`impl` (Entity, Repository, ServiceImpl):** Veritabanı tablolarını, ilişkilerini ve kayıt işlemlerini yönetir.

## 🗄️ Veritabanı İlişkileri (JPA)
Projede veri bütünlüğünü sağlamak için aşağıdaki `One-To-Many` ve `Many-To-One` ilişkileri kurulmuştur:
- **User ↔ Order:** Bir kullanıcının birden fazla siparişi olabilir.
- **Category ↔ Product:** Bir kategoride birden fazla ürün bulunabilir.
- **Company ↔ Store:** Bir şirketin birden fazla mağazası olabilir.

*(Not: ID yönetimi gereksiz sequence tabloları oluşturulmaması adına `GenerationType.IDENTITY` stratejisi ile doğrudan MySQL'e bırakılmıştır).*

## ⚙️ Kurulum ve Çalıştırma
1. MySQL'de `ecommerce_db` adında boş bir veritabanı oluşturun.
2. `src/main/resources/application.properties` dosyasındaki `spring.datasource.password` alanını kendi MySQL şifrenizle güncelleyin.
3. Projeyi çalıştırın:
   ```bash
   mvn spring-boot:run
   ```
4. Spring Boot ve Hibernate, projedeki tüm tabloları ve ilişkileri veritabanında **otomatik olarak** oluşturacaktır. Artık Postman üzerinden istek atarak test etmeye başlayabilirsiniz!
