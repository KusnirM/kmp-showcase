package mk.digital.kmpsample.presentation.base.router

actual class ExternalRouter :
    DialRouter by DialRouterImpl(),
    LinkRouter by LinkRouterImpl(),
    ShareRouter by ShareRouterImpl()
