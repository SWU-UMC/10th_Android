package com.example.nike.di;

import com.example.nike.data.remote.api.ReqResService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
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
public final class NetworkModule_ProvideReqResServiceFactory implements Factory<ReqResService> {
  private final Provider<Retrofit> retrofitProvider;

  private NetworkModule_ProvideReqResServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public ReqResService get() {
    return provideReqResService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideReqResServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideReqResServiceFactory(retrofitProvider);
  }

  public static ReqResService provideReqResService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideReqResService(retrofit));
  }
}
