package com.example.esraarashad.httpurlconnectionexample.basemvp

abstract class BaseRepository: BaseContract.BaseIRepository {
    val remoteDataSource = RemoteDataSource.instance
    val localDataSource = LocalDataSource.instance
}