package com.example.nike.data.local;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class ProductDataStore_Factory implements Factory<ProductDataStore> {
  private final Provider<Context> contextProvider;

  private ProductDataStore_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ProductDataStore get() {
    return newInstance(contextProvider.get());
  }

  public static ProductDataStore_Factory create(Provider<Context> contextProvider) {
    return new ProductDataStore_Factory(contextProvider);
  }

  public static ProductDataStore newInstance(Context context) {
    return new ProductDataStore(context);
  }
}
