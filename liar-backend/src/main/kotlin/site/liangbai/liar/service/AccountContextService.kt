package site.liangbai.liar.service

import site.liangbai.liar.entity.dto.auth.UserAuthorize

interface AccountContextService {
    var currentAccount: UserAuthorize?
}