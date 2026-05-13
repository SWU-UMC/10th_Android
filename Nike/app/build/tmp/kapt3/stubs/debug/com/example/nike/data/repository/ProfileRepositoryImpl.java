package com.example.nike.data.repository;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0096@\u00a2\u0006\u0004\b\u000b\u0010\fJ$\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000e0\u00072\u0006\u0010\u000f\u001a\u00020\nH\u0096@\u00a2\u0006\u0004\b\u0010\u0010\fJ\f\u0010\u0011\u001a\u00020\b*\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/example/nike/data/repository/ProfileRepositoryImpl;", "Lcom/example/nike/domain/repository/ProfileRepository;", "service", "Lcom/example/nike/data/remote/api/ReqResService;", "<init>", "(Lcom/example/nike/data/remote/api/ReqResService;)V", "getProfile", "Lkotlin/Result;", "Lcom/example/nike/ui/profile/ProfileUserUiModel;", "userId", "", "getProfile-gIAlu-s", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFollowingUsers", "", "page", "getFollowingUsers-gIAlu-s", "toUiModel", "Lcom/example/nike/data/remote/dto/ReqResUserDto;", "app_debug"})
public final class ProfileRepositoryImpl implements com.example.nike.domain.repository.ProfileRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.nike.data.remote.api.ReqResService service = null;
    
    @javax.inject.Inject()
    public ProfileRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.example.nike.data.remote.api.ReqResService service) {
        super();
    }
    
    private final com.example.nike.ui.profile.ProfileUserUiModel toUiModel(com.example.nike.data.remote.dto.ReqResUserDto $this$toUiModel) {
        return null;
    }
}