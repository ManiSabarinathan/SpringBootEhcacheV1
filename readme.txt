Spring Boot application for Ehcache


application.properties:

# Database
server.port=8088
#spring.datasource.driver-class-name: com.mysql.jdbc.Driver
spring.datasource.driver-class-name:com.mysql.cj.jdbc.Driver
spring.datasource.url: jdbc:mysql://localhost:3306/ITEMDB
spring.datasource.username: root
spring.datasource.password: root

management.endpoints.web.exposure.include=*
spring.cache.cache-names=itemCache
spring.cache.type=ehcache
spring.cache.ehcache.config=classpath:ehcache.xml


ehcache.xml
-----------

<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNameSpaceSchemaLocation="ehcache.xsd"
         updateCheck="true"
         monitoring="autodetect"
         dynamicConfig="true">
         <!--   <diskStore path="java.io.tmpdir"/>  C:\Users\yh06759\AppData\Local\Temp  
         -->
    <diskStore path="c:\\smani\\tempEhcache\\" />
    <cache name="itemCache"
            maxEntriesLocalHeap="1000"
            maxEntriesLocalDisk="1000"
            eternal="false"
            diskSpoolBufferSizeMB="20"
            timeToIdleSeconds="300"
            timeToLiveSeconds="30"
            memoryStoreEvictionPolicy="LFU"
            transactionalMode="off">
        <persistence strategy="localTempSwap"/>
    </cache>

</ehcache>
