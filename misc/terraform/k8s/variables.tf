variable appId {}
variable password {}
variable tenant {}

variable "location" {
  default = "northeurope"
}

variable "resource_group" {
  default     = "eadbprg"
}

variable "acr_name" {
  default = "eadbpacr"
}

variable "aks_name" {
  default = "eadbpaks"
}
