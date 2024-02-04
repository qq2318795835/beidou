<template>
  <div>
    <svg-icon icon-class="question" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'beidouDoc',
  data() {
    return {
      url: 'http://doc.beidou.vip/beidou-vue'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>