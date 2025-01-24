package site.liangbai.liar.service.auth

import site.liangbai.liar.entity.dto.auth.UserAuthorize

interface AccountContextService {
    var currentAccount: UserAuthorize?
}