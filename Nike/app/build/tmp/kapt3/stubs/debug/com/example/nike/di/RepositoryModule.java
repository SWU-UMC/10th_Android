package com.example.nike.di;

@dagger.Module()
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\'J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\nH\'\u00a8\u0006\u000b"}, d2 = {"Lcom/example/nike/di/RepositoryModule;", "", "<init>", "()V", "bindProductRepository", "Lcom/example/nike/domain/repository/ProductRepository;", "impl", "Lcom/example/nike/data/repository/ProductRepositoryImpl;", "bindProfileRepository", "Lcom/example/nike/domain/repository/ProfileRepository;", "Lcom/example/nike/data/repository/ProfileRepositoryImpl;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class RepositoryModule {
    
    public RepositoryModule() {
        super();
    }
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.nike.domain.repository.ProductRepository bindProductRepository(@org.jetbrains.annotations.NotNull()
    com.example.nike.data.repository.ProductRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.nike.domain.repository.ProfileRepository bindProfileRepository(@org.jetbrains.annotations.NotNull()
    com.example.nike.data.repository.ProfileRepositoryImpl impl);
}