package com.example.nike.data.repository;

import com.example.nike.data.local.ProductDataStore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class ProductRepositoryImpl_Factory implements Factory<ProductRepositoryImpl> {
  private final Provider<ProductDataStore> dataStoreProvider;

  private ProductRepositoryImpl_Factory(Provider<ProductDataStore> dataStoreProvider) {
    this.dataStoreProvider = dataStoreProvider;
  }

  @Override
  public ProductRepositoryImpl get() {
    return newInstance(dataStoreProvider.get());
  }

  public static ProductRepositoryImpl_Factory create(Provider<ProductDataStore> dataStoreProvider) {
    return new ProductRepositoryImpl_Factory(dataStoreProvider);
  }

  public static ProductRepositoryImpl newInstance(ProductDataStore dataStore) {
    return new ProductRepositoryImpl(dataStore);
  }
}
