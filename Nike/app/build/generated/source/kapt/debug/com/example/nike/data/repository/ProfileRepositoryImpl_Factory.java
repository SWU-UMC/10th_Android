package com.example.nike.data.repository;

import com.example.nike.data.remote.api.ReqResService;
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
public final class ProfileRepositoryImpl_Factory implements Factory<ProfileRepositoryImpl> {
  private final Provider<ReqResService> serviceProvider;

  private ProfileRepositoryImpl_Factory(Provider<ReqResService> serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  @Override
  public ProfileRepositoryImpl get() {
    return newInstance(serviceProvider.get());
  }

  public static ProfileRepositoryImpl_Factory create(Provider<ReqResService> serviceProvider) {
    return new ProfileRepositoryImpl_Factory(serviceProvider);
  }

  public static ProfileRepositoryImpl newInstance(ReqResService service) {
    return new ProfileRepositoryImpl(service);
  }
}
