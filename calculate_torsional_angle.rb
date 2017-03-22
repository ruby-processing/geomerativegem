def calculate_torsional_angle(at0, at1, at2, at3)
  r01 = at0 - at1
  r32 = at3 - at2
  r12 = at1 - at2
  p = r12.cross(r01)
  q = r12.cross(r32)
  r = r12.cross(q)
  u = q.dot(q)
  v = r.dot(r)
  return 360.0 if (u <= 0.0 || v <= 0.0)
  u1 = p.dot(q) # u1 = p * q
  v1 = p.dot(r) # v1 = p * r
  u = u1 / sqrt(u)
  v = v1 / sqrt(v)
  return Math.atan2(v, u).degrees if (u.abs > 0.01 || v.abs > 0.01)
  360.0
end
