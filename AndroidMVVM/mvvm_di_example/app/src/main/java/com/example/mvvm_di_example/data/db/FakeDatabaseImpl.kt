package com.example.mvvm_di_example.data.db

class FakeDatabaseImpl : Database {
    //여기서 DI를 해주지 않는 이유는 FakeDatabaseImpl은 항상 QuoteDaoFakeImpl을 가지기 때문임.
    //FakeDatabaseImpl을 Singleton으로 관리해주면 quoteDao도 항상 같은 인스턴스를 반환받을 수 있음
    override val quoteDao =  QuoteDaoFakeImpl()
}