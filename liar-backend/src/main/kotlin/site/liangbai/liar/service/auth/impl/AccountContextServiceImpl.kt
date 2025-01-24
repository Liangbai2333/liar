package site.liangbai.liar.service.auth.impl

import org.springframework.stereotype.Service
import site.liangbai.liar.entity.dto.auth.UserAuthorize
import site.liangbai.liar.service.auth.AccountContextService

@Service
class AccountContextServiceImpl : AccountContextService {
    private val accountThreadLocal: ThreadLocal<UserAuthorize> = ThreadLocal<UserAuthorize>()

    override var currentAccount: UserAuthorize?
        get() = accountThreadLocal.get()
        set(account) {
            accountThreadLocal.set(account)
        }
}